import axios from 'axios';
import { environment } from 'src/environment/environment';
const instance = axios.create({
  baseURL: environment.baseUrl,
});

import { ITask } from 'src/interfaces/ITask';

export function CreateTask(task: ITask) {
  return instance.post(`${environment.baseUrl}tasks`, task);
}

export function GetUserTasks(id: number, status: string) {
  return instance.get(`${environment.baseUrl}tasks`, {
    params: {
      id,
      status,
    },
  });
}




export function changeStatusTask(id: number, status: string) {
  return instance.put(`${environment.baseUrl}tasks/status`, {
    id: id,
    status: status,
  });
}
