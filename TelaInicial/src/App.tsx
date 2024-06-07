import { useState } from 'react';
import './App.css'
import { Card } from './components/card/card';
import { useVehicleData } from './hooks/useVehicleData';
import { CreateModal } from './components/create-modal/create-modal';

function App() {
  const { data } = useVehicleData();
  const [isModalOpen, setIsModalOpen] = useState(false);

  const handleOpenModal = () =>{
    setIsModalOpen(prev => !prev);
  }

  return (
      <div className='container'>
        <h1 className='veiculos'>Veículos</h1>
        <div className="card-grid">
          {data?.map(vehicleData => 
            <Card 
            price={vehicleData.price} 
            nome={vehicleData.nome} 
            image={vehicleData.image}
            />
            )}
            {isModalOpen && <CreateModal closeModal={handleOpenModal}/>}
            <button onClick={handleOpenModal}>novo</button>
        </div>
      </div>
  )
}

export default App
