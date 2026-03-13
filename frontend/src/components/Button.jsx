import React from 'react';

const Button = ({ children, onClick, type = 'button', variant = 'primary', className = '', ...props }) => {
  const baseStyle = 'inline-flex justify-center items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md shadow-sm focus:outline-none focus:ring-2 focus:ring-offset-2 transition-colors duration-200';
  
  const variants = {
    primary: 'text-white bg-blue-600 hover:bg-blue-700 focus:ring-blue-500',
    secondary: 'text-blue-700 bg-blue-100 hover:bg-blue-200 focus:ring-blue-500',
    danger: 'text-white bg-red-600 hover:bg-red-700 focus:ring-red-500',
    outline: 'border-gray-300 text-gray-700 bg-white hover:bg-gray-50 focus:ring-blue-500'
  };

  return (
    <button
      type={type}
      onClick={onClick}
      className={`${baseStyle} ${variants[variant]} ${className}`}
      {...props}
    >
      {children}
    </button>
  );
};

export default Button;
