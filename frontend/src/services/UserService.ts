import axios from 'axios';
import { environment } from 'src/environment/environment';
import { Match } from 'src/types/IMatch';
import { User } from 'src/interfaces/IUser';

const instance = axios.create({
  baseURL: environment.baseUrl,
});

export function GetUserById(id: number) {
  return instance.get(`${environment.baseUrl}users/${id}`);
}

export function Login(user: User) {
  return instance.post(`${environment.baseUrl}users/login`, user);
}

export function CreateUser(user: User) {
  return instance.post(`${environment.baseUrl}users`, user);
}

// export function CadastroUser(data: Register) {
//   return instance.post(`${environment.baseUrl}/api/v1/usuarios/signUp`, data);
// }
