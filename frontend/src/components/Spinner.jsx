import React from 'react';

const Spinner = ({ size = 'md', color = 'blue' }) => {
  const sizeClass = {
    sm: 'h-4 w-4 border-2',
    md: 'h-8 w-8 border-4',
    lg: 'h-12 w-12 border-4',
  }[size];

  const colorClass = {
    blue: 'border-blue-500 border-t-transparent',
    white: 'border-white border-t-transparent',
  }[color];

  return (
    <div className="flex justify-center items-center">
      <div className={`animate-spin rounded-full ${sizeClass} ${colorClass}`}></div>
    </div>
  );
};

export default Spinner;
