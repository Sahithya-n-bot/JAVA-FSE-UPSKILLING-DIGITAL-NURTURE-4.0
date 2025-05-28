1. User Upcoming Events:

SELECT 
    u.full_name AS "User Name", 
    e.title AS "Event Title", 
    e.start_date AS "Event Date"
FROM 
    Users u
INNER JOIN 
    Registrations r ON u.user_id = r.user_id
INNER JOIN 
    Events e ON r.event_id = e.event_id
WHERE 
    e.status = 'upcoming' 
    AND u.city = e.city
ORDER BY 
    e.start_date ASC;


2. Top Rated Events:

SELECT 
    e.title AS "Event Title", 
    ROUND(AVG(f.rating), 2) AS "Average Rating", 
    COUNT(*) AS "Feedback Count"
FROM 
    Feedback f
INNER JOIN 
    Events e ON f.event_id = e.event_id
GROUP BY 
    e.event_id, e.title
HAVING 
    COUNT(*) >= 10
ORDER BY 
    AVG(f.rating) DESC;

3. Inactive Users (no registrations in last 90 days):

SELECT 
    u.*
FROM 
    Users u
LEFT JOIN 
    Registrations r ON u.user_id = r.user_id 
    AND r.registration_date >= CURDATE() - INTERVAL 90 DAY
WHERE 
    r.user_id IS NULL;


4. Peak Session Hours (10 AM to 12 PM):

SELECT 
    e.title AS "Event Title", 
    COUNT(*) AS "Session Count"
FROM 
    Sessions s
INNER JOIN 
    Events e ON s.event_id = e.event_id
WHERE 
    HOUR(s.start_time) BETWEEN 10 AND 11
GROUP BY 
    s.event_id, e.title;


5. Most Active Cities:

SELECT 
    u.city AS "City", 
    COUNT(DISTINCT r.user_id) AS "Total Users"
FROM 
    Users u
INNER JOIN 
    Registrations r ON u.user_id = r.user_id
GROUP BY 
    u.city
ORDER BY 
    COUNT(DISTINCT r.user_id) DESC
LIMIT 5;


6. Event Resource Summary:

SELECT 
    e.title AS "Event Title", 
    SUM(CASE WHEN resource_type = 'pdf' THEN 1 ELSE 0 END) AS "PDFs",
    SUM(CASE WHEN resource_type = 'image' THEN 1 ELSE 0 END) AS "Images",
    SUM(CASE WHEN resource_type = 'link' THEN 1 ELSE 0 END) AS "Links"
FROM 
    Resources r
INNER JOIN 
    Events e ON r.event_id = e.event_id
GROUP BY 
    r.event_id, e.title;


7. Low Feedback Alerts:

SELECT 
    u.full_name AS "User Name", 
    e.title AS "Event Title", 
    f.rating AS "Rating", 
    f.comments AS "Feedback"
FROM 
    Feedback f
INNER JOIN 
    Users u ON f.user_id = u.user_id
INNER JOIN 
    Events e ON f.event_id = e.event_id
WHERE 
    f.rating < 3
ORDER BY 
    f.rating ASC;


8. Sessions per Upcoming Event:

SELECT 
    e.title AS "Event Title", 
    COALESCE(COUNT(s.session_id), 0) AS "Total Sessions"
FROM 
    Events e
LEFT JOIN 
    Sessions s ON e.event_id = s.event_id
WHERE 
    e.status = 'upcoming'
GROUP BY 
    e.event_id, e.title
ORDER BY 
    COUNT(s.session_id) DESC;


9. Organizer Event Summary:

SELECT 
    u.full_name AS "Organizer Name", 
    e.status AS "Event Status", 
    COUNT(*) AS "Event Count"
FROM 
    Events e
INNER JOIN 
    Users u ON e.organizer_id = u.user_id
GROUP BY 
    u.user_id, u.full_name, e.status
ORDER BY 
    u.full_name, e.status;


10. Feedback Gap:

SELECT DISTINCT 
    e.title AS "Event Title"
FROM 
    Events e
INNER JOIN 
    Registrations r ON e.event_id = r.event_id
LEFT JOIN 
    Feedback f ON e.event_id = f.event_id
WHERE 
    f.event_id IS NULL;

);

11. Daily New User Count (last 7 days):

SELECT 
    registration_date AS "Registration Date", 
    COUNT(*) AS "New Users"
FROM 
    Users
WHERE 
    registration_date >= CURDATE() - INTERVAL 7 DAY
GROUP BY 
    registration_date
ORDER BY 
    registration_date ASC;


12. Event with Maximum Sessions:

SELECT 
    e.title AS "Event Title", 
    COUNT(s.session_id) AS "Total Sessions"
FROM 
    Events e
INNER JOIN 
    Sessions s ON e.event_id = s.event_id
GROUP BY 
    e.event_id, e.title
HAVING 
    COUNT(s.session_id) = (
        SELECT 
            MAX(session_count) 
        FROM (
            SELECT 
                event_id, 
                COUNT(*) AS session_count 
            FROM 
                Sessions 
            GROUP BY 
                event_id
        ) AS subquery
    );

13. Average Rating per City:

SELECT 
    e.city AS "City", 
    ROUND(AVG(f.rating), 2) AS "Average Rating"
FROM 
    Feedback f
INNER JOIN 
    Events e ON f.event_id = e.event_id
GROUP BY 
    e.city
ORDER BY 
    AVG(f.rating) DESC;


14. Most Registered Events:

SELECT 
    e.title AS "Event Title", 
    COUNT(r.user_id) AS "Total Registrations"
FROM 
    Events e
INNER JOIN 
    Registrations r ON e.event_id = r.event_id
GROUP BY 
    e.event_id, e.title
ORDER BY 
    COUNT(r.user_id) DESC
LIMIT 3;


15. Event Session Time Conflict:

SELECT 
    s1.event_id AS "Event ID", 
    s1.session_id AS "Session 1 ID", 
    s2.session_id AS "Session 2 ID"
FROM 
    Sessions s1
INNER JOIN 
    Sessions s2 ON s1.event_id = s2.event_id
    AND s1.session_id < s2.session_id
    AND s1.start_time < s2.end_time
    AND s2.start_time < s1.end_time;

16. Unregistered Active Users:

SELECT 
    u.*
FROM 
    Users u
LEFT JOIN 
    Registrations r ON u.user_id = r.user_id
WHERE 
    u.registration_date >= CURDATE() - INTERVAL 30 DAY
    AND r.user_id IS NULL;

17. Multi-Session Speakers:

SELECT 
    speaker_name AS "Speaker Name", 
    COUNT(*) AS "Total Sessions"
FROM 
    Sessions
GROUP BY 
    speaker_name
HAVING 
    COUNT(*) > 1
ORDER BY 
    COUNT(*) DESC;


18. Events Without Resources:

SELECT 
    e.title AS "Event Title"
FROM 
    Events e
LEFT JOIN 
    Resources r ON e.event_id = r.event_id
WHERE 
    r.event_id IS NULL;


19. Completed Events with Feedback Summary:

SELECT 
    e.title AS "Event Title", 
    COUNT(DISTINCT r.user_id) AS "Total Registrations", 
    ROUND(AVG(f.rating), 2) AS "Average Rating"
FROM 
    Events e
LEFT JOIN 
    Registrations r ON e.event_id = r.event_id
LEFT JOIN 
    Feedback f ON e.event_id = f.event_id
WHERE 
    e.status = 'completed'
GROUP BY 
    e.event_id, e.title
ORDER BY 
    COUNT(DISTINCT r.user_id) DESC;


20. User Engagement Index:

SELECT 
    u.full_name AS "User Name", 
    COUNT(DISTINCT r.event_id) AS "Events Attended", 
    COUNT(DISTINCT f.feedback_id) AS "Feedbacks Given"
FROM 
    Users u
LEFT JOIN 
    Registrations r ON u.user_id = r.user_id
LEFT JOIN 
    Feedback f ON u.user_id = f.user_id
GROUP BY 
    u.user_id, u.full_name
ORDER BY 
    COUNT(DISTINCT r.event_id) DESC;


21. Top Feedback Providers:

SELECT 
    u.full_name AS "User Name", 
    COUNT(f.feedback_id) AS "Feedbacks Given"
FROM 
    Feedback f
INNER JOIN 
    Users u ON f.user_id = u.user_id
GROUP BY 
    u.user_id, u.full_name
ORDER BY 
    COUNT(f.feedback_id) DESC
LIMIT 5;

22. Duplicate Registrations Check:
SELECT 
    user_id, 
    event_id, 
    COUNT(*) AS total_registrations
FROM 
    Registrations
GROUP BY 
    user_id, event_id
HAVING 
    COUNT(*) > 1;


If the latter (users who registered for more than one event):


SELECT 
    user_id, 
    COUNT(DISTINCT event_id) AS total_events
FROM 
    Registrations
GROUP BY 
    user_id
HAVING 
    COUNT(DISTINCT event_id) > 1;


23. Registration Trends (Monthly for last 12 months):

SELECT 
    DATE_FORMAT(registration_date, '%Y-%m') AS "Registration Month", 
    COUNT(*) AS "Total Registrations"
FROM 
    Registrations
WHERE 
    registration_date >= CURDATE() - INTERVAL 12 MONTH
GROUP BY 
    DATE_FORMAT(registration_date, '%Y-%m')
ORDER BY 
    "Registration Month" ASC;


24. Average Session Duration per Event:

SELECT 
    e.title AS "Event Title", 
    ROUND(AVG(TIMESTAMPDIFF(MINUTE, s.start_time, s.end_time)), 2) AS "Average Session Duration (Minutes)"
FROM 
    Events e
INNER JOIN 
    Sessions s ON e.event_id = s.event_id
GROUP BY 
    e.event_id, e.title
ORDER BY 
    AVG(TIMESTAMPDIFF(MINUTE, s.start_time, s.end_time)) DESC;


25. Events Without Sessions:

SELECT 
    e.title AS "Event Title"
FROM 
    Events e
LEFT JOIN 
    Sessions s ON e.event_id = s.event_id
WHERE 
    s.event_id IS NULL;
