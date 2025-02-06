
import React from 'react'
import { useParams } from 'react-router-dom'


export const ViewHabit = () => {
  const {id} = useParams();
  return (
    <div>{`Viendo habito con id: ${id}`}</div>
  )
}