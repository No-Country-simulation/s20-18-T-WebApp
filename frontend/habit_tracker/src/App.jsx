import { useState } from 'react'
import { Routes, Route } from 'react-router-dom'

import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'

import './App.css'
import { Footer, Header } from './components/index';
import { Home, Dashboard, Profile, Settings } from './pages/index';

function App() {
  return (
    <>    
      <Header />
      <main className='main-container'>
        <Routes>
          <Route path="/" element={<Home />}/>        
          <Route path="/dashboard" element={<Dashboard />}/>    
          <Route path="/profile" element={<Profile />}/>    
          <Route path="/settings" element={<Settings />}/>    
        </Routes>   
      <Footer />
      </main>
    </>
  )
}
export default App
