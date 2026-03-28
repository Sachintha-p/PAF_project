import React, { useState, useEffect } from 'react';
import api from '../api/axios';

const Resources = () => {
    const [resources, setResources] = useState([]);

    useEffect(() => {
        const fetchResources = async () => {
            try {
                const response = await api.get('/resources');
                // Handles the nested ApiResponse structure we set up in the backend
                setResources(response.data.data || response.data);
            } catch (error) {
                console.error("Failed to fetch resources:", error);
            }
        };
        fetchResources();
    }, []);

    const handleBook = async (resourceId) => {
        // Set a dummy booking for tomorrow to test the flow
        const tomorrow = new Date();
        tomorrow.setDate(tomorrow.getDate() + 1);
        
        // FIX: Strip the 'Z' and milliseconds to match Spring Boot's LocalDateTime
        const formatForJava = (date) => {
            return date.toISOString().split('.')[0]; 
        };

        const bookingData = {
            resourceId: resourceId,
            startTime: formatForJava(tomorrow),
            endTime: formatForJava(new Date(tomorrow.getTime() + 2 * 60 * 60 * 1000)) // Books for 2 hours
        };

        try {
            const response = await api.post('/bookings', bookingData);
            alert("Success! Booking created. Check the Admin > Bookings tab.");
            console.log("Response:", response.data);
        } catch (error) {
            console.error("Booking failed:", error.response?.data || error.message);
            alert("Failed to book. Check console for details.");
        }
    };

    return (
        <div className="container mx-auto p-4">
            <h2 className="text-2xl font-bold mb-4">Resource Catalogue</h2>
            
            {resources && resources.length > 0 ? (
                <table className="min-w-full bg-white border">
                    <thead>
                        <tr>
                            <th className="py-2 px-4 border-b text-left">NAME</th>
                            <th className="py-2 px-4 border-b text-left">TYPE</th>
                            <th className="py-2 px-4 border-b text-left">CAPACITY</th>
                            <th className="py-2 px-4 border-b text-left">LOCATION</th>
                            <th className="py-2 px-4 border-b text-left">STATUS</th>
                            <th className="py-2 px-4 border-b text-left">ACTION</th> 
                        </tr>
                    </thead>
                    <tbody>
                        {resources.map(resource => (
                            <tr key={resource.id}>
                                <td className="py-2 px-4 border-b">{resource.name}</td>
                                <td className="py-2 px-4 border-b">{resource.type}</td>
                                <td className="py-2 px-4 border-b">{resource.capacity}</td>
                                <td className="py-2 px-4 border-b">{resource.location}</td>
                                <td className="py-2 px-4 border-b">
                                    <span className={`px-2 py-1 rounded text-sm ${resource.status === 'ACTIVE' ? 'bg-green-100 text-green-800' : 'bg-red-100 text-red-800'}`}>
                                        {resource.status}
                                    </span>
                                </td>
                                <td className="py-2 px-4 border-b">
                                    <button 
                                        onClick={() => handleBook(resource.id)}
                                        className="bg-blue-600 text-white px-3 py-1 rounded hover:bg-blue-700 transition-colors"
                                    >
                                        Book Now
                                    </button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            ) : (
                <div className="bg-white p-6 border text-center text-gray-500">
                    No data available.
                </div>
            )}
        </div>
    );
};

export default Resources;