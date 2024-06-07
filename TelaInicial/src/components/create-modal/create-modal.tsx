import { useEffect, useState } from "react";
import { useVehicleDataMutate } from "../../hooks/useVehicleDataMutate";
import { VehicleData } from "../../interface/VehicleData";

import "./modal.css";

interface InputProps{
    label: string,
    value: string | number,
    updateValue(value: any): void
}

interface ModalProps{
    closeModal(): void
}

const Input = ({ label, value, updateValue}: InputProps) =>{
    return (
        <>
            <label>{label}</label>
            <input value={value} onChange={e => updateValue(e.target.value)}></input>
        </>
    )
}

export function CreateModal({ closeModal }: ModalProps) {
    const [nome, setNome] = useState("");
    const [marca, setMarca] = useState("");
    const [modelo, setModelo] = useState("");
    const [price, setPrice] = useState(0);
    const [image, setImage] = useState("");
    const { mutate, isSuccess }= useVehicleDataMutate();

    const submit = () =>{
        const vehicleData: VehicleData = {
            nome,
            marca,
            modelo,
            price,
            image
        }

        mutate(vehicleData);
    }

    useEffect(() => {
        if(!isSuccess)return 
        closeModal()
    }, [isSuccess])

    return (
        <div className="modal-overlay">
            <div className="modal-body">
                <h2>Cadastre um novo veículo</h2>
                <form className="input-container">
                    <Input label="nome" value={nome} updateValue={setNome}/>
                    <Input label="marca" value={marca} updateValue={setMarca}/>
                    <Input label="modelo" value={modelo} updateValue={setModelo}/>
                    <Input label="price" value={price} updateValue={setPrice}/>
                    <Input label="image" value={image} updateValue={setImage}/> 
                </form>
                <button onClick={submit} className="btn-secondary">Postar</button>
            </div>
        </div>
    );
}

