# Cursors

What is the feature?

Collaborative cursors, which allow users to see where other users are in the document.

How does it work?

The client sends a cursor update to the server every time the cursor position changes. The server then broadcasts the cursor update to all other clients.

---

## Implementation

### Client

The client uses a reference to the CursorApi to send cursor updates to the server. The CursorApi is a singleton that is initialized when the client is initialized. The CursorApi is initialized with the user id, which is used to identify the user belongs to.

Right now, user ids are valid hex colors, so the color of the cursor is the same as the user id. I would like to keep this behavior, but I would also like to allow users to customize their cursor color.

The CursorApi is initialized with a reference to the WebSocketApi, which is used to send cursor updates to the server. CursorUpdates are a simple data class that contains the user id and the cursor position.

### Server
 
The server contains a CursorEndpoint, which is a WebSocketEndpoint that handles cursor updates among clients. The CursorEndpoint is initialized with a reference to the event bus. 

CursorActor is a simple actor that simulates a user moving a cursor around the document. It is used to test the CursorEndpoint, as well as provide a means of which to benchmark the performance of the client and server. 

Of note, the CursorSimEndpoint is responsible for managing CursorActors. In practice this means it stores a hasmap of user ids to CursorActors, which happen to be threads.

## Performance

### Testing

I used the CursorActor to test the performance of the CursorEndpoint. The CursorActor sends a cursor update every 100 milliseconds. The CursorEndpoint then broadcasts the cursor update to all other clients. The CursorEndpoint can handle an indefinite amount of traffic without any noticeable performance degradation. The client however, does not perform as well. The client can handle about 100 cursor updates per second before the cursor starts to lag behind the CursorActor. This is not a problem for the current use case, but it is something that I would like to improve in the future. Which means I will most likely instate a limit to the number of concurrent users that can be supported. 10 users per session/document seems like a reasonable limit.

### Demo

```bash

# Bring the stack up
$ ./gradlew :deployment:compose

# Wait about 30 seconds for the stack to come up
# You can follow the logs with
$ cd deployment/compose && docker-compose logs -f

# Open the browser
$ open http://localhost/cursors

# Open the browser again (in another window)
$ open http://localhost/cursors

# Move the cursor around in both windows
# You should see the cursor move in both windows

# Now make a CursorActor and watch the cursor move
# run the next command as many times as you want
$ curl -X PUT -H "Content-Type: application/json" http://localhost/api/cursors/sim

# Notice the waterfall of cursors in the browser
```