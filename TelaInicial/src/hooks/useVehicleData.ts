import axios, { AxiosPromise } from "axios"
import { VehicleData } from "../interface/VehicleData";
import { useQuery } from "@tanstack/react-query";
const API_URL = 'http://localhost:8080';

const fetchData = async(): AxiosPromise<VehicleData[]> => {
    const response = axios.get(API_URL + "/vehicles");
    return response;
}

export function useVehicleData(){
    const query = useQuery({
        queryFn: fetchData,
        queryKey: ['vehicle-data'],
        retry: 2
    })

    return{
        ...query,
        data: query.data?.data
    }
}