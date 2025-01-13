import React from 'react'
import { Link } from 'react-router-dom';
import './Header.module.css';

export const Header = () => {
  return (
    <header>      
      <ul>
        <li><Link to='/'>Home</Link></li>
        <li><Link to='/dashboard'>Dashboard</Link></li>
        <li><Link to='/profile'>Profile</Link></li>
        <li><Link to='/settings'>Settings</Link></li>
      </ul>
    </header>
  )
}