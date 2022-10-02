import React, {useEffect} from "react";

/*
    The Home View is the first view that the user sees when they log in.
    It is a simple view that displays a welcome message and a button to
    navigate to the other views.

 */

export function Home() {

    const [time,setTime] = React.useState(0);
    const [date, setDate] = React.useState("");
    const [shouldFetchTime,setShouldFetchTime] = React.useState(true);
    useEffect(() => {
        const fetchTime = async () => {
            const response = await fetch('/api/time');
            const response_object = await response.json();
            setTime(response_object?.time);
        }

        const fetchDate = async () => {
            const response = await fetch('/api/date');
            const response_object = await response.json();
            setDate(response_object?.date);
        }

        Promise.all(
            [
                fetchTime(),
                fetchDate()
            ])
            .then(() => setShouldFetchTime(false))
            .catch(() => setShouldFetchTime(false));

    },[shouldFetchTime]);

    useEffect(() => {
        const interval = setInterval(() => {
            setShouldFetchTime(true);
        }, 1000);
        return () => clearInterval(interval);
    }, []);

    return (
        <div>
            <h1>Welcome to the Home View</h1>
            <p>Click on the links above to navigate to the other views.</p>
            <p>The time is: {time}</p>
            <p>The date is: {date}</p>
        </div>
    );
}
