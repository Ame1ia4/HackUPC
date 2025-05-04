// (Conceptual - Requires user identification and connection tracking)
const WebSocket = require('ws');
const wss = new WebSocket.Server({ port: 8080 });
const users = new Map(); // Map of user IDs to WebSocket connections

wss.on('connection', ws => {
    // --- In a real app, you'd authenticate the user here and get their ID ---
    const userId = generateUniqueId(); // Replace with actual user identification

    console.log(`User ${userId} connected.`);
    users.set(userId, ws);

    ws.on('message', message => {
        try {
            const messageData = JSON.parse(message);
            const senderId = userId; // The current user is the sender
            const recipientId = messageData.recipient;
            const text = messageData.message;

            const recipientWs = users.get(recipientId);

            if (recipientWs && recipientWs.readyState === WebSocket.OPEN) {
                const response = JSON.stringify({ sender: 'otherUser', message: text });
                recipientWs.send(response);
                // Optionally, send confirmation back to the sender
                ws.send(JSON.stringify({ sender: 'you', message: text }));
            } else {
                // Handle case where recipient is offline
                ws.send(JSON.stringify({ sender: 'system', message: 'Recipient is offline.' }));
            }
        } catch (error) {
            console.error('Failed to parse message or send:', error);
        }
    });

    ws.on('close', () => {
        console.log(`User ${userId} disconnected.`);
        users.delete(userId);
    });

    ws.on('error', error => {
        console.error('WebSocket error for user ${userId}:', error);
        users.delete(userId);
    });
});

function generateUniqueId() {
    return Math.random().toString(36).substring(2, 15);
}

console.log('WebSocket server started on port 8080.');