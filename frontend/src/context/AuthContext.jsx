import React, { createContext, useContext, useState, useEffect } from 'react';
import { authService } from '../api/authService';
import Spinner from '../components/Spinner';

const AuthContext = createContext(null);

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchUser = async () => {
      try {
        const response = await authService.getCurrentUser();
        // Adjust depending on actual ApiResponse wrapper structure handling in axios response interceptor
        // Currently structured as data { success: true, message: "", data: {} }
        if (response.success && response.data) {
          setUser(response.data);
        }
      } catch (error) {
        console.error("Failed to fetch user, user might not be logged in:", error);
      } finally {
        setLoading(false);
      }
    };

    fetchUser();
  }, []);

  const value = {
    user,
    setUser,
    loading,
    isAdmin: user?.role === 'ADMIN',
    isTechnician: user?.role === 'TECHNICIAN'
  };

  if (loading) {
    return <div className="h-screen flex justify-center items-center"><Spinner size="lg" /></div>;
  }

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
};

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error('useAuth must be used within an AuthProvider');
  }
  return context;
};
