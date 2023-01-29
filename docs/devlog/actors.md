# Actors

An actor connected to the the Cursor Room that has access to the current
document
for that specific room. It can move the cursor around, click, and type.

In order to perform any behaviour intelligently, the actor needs to know the
current document.

Each cursor room stores a URL to current document. The actor can use this URL to
fetch the document from the server.
Additionally, this URL is used by other connected clients.

So a real human connects to the cursor room, providing the room with a URL to
the document. Then, the actor connects to the same room, and can use the URL to
fetch the document. The actor has to be able to render the document using a
headless browser, and then move the cursor around, click, and type.
