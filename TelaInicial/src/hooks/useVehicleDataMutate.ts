import { useMutation, useQueryClient } from "@tanstack/react-query";
import axios, { AxiosPromise } from "axios"
import { VehicleData } from "../interface/VehicleData";

const API_URL = 'http://localhost:8080';

const postData = async(data: VehicleData): AxiosPromise<any> => {
    const response = axios.post(API_URL + "/vehicles", data);
    return response;
}

export function useVehicleDataMutate(){
    const queryClient = useQueryClient();
    const mutate = useMutation({
        mutationFn: postData,
        retry: 2,
        onSuccess: () => {
            queryClient.invalidateQueries({queryKey: ['vehicle-data']});
        }
    });

    return mutate;
}