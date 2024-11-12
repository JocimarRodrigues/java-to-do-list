import axios from 'axios';
import { environment } from 'src/environment/environment';
const instance = axios.create({
  baseURL: environment.baseUrl,
});

export function GetUserTasks(id: number, status: string) {
  return instance.get(`${environment.baseUrl}tasks`, {
    params: {
      id,
      status,
    },
  });
}
