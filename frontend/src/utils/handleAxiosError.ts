import { AxiosError } from 'axios';
import { triggerNegative } from './triggers';

type IAxiosError = {
  errors: string[];
  message: string;
};

export const handleAxiosError = (error: AxiosError<IAxiosError>) => {
  if (!error.response) {
    triggerNegative('Erro de conexão ou tempo de resposta excedido.');
    console.error('Erro de rede:', error);
    return;
  }

  const { data } = error.response;

  if (Array.isArray(data?.errors)) {
    data.errors.forEach((err: string) => triggerNegative(err));
  } else {
    const message = data?.message || 'Erro inesperado no servidor.';
    triggerNegative(message);
  }

  console.error('Erro no Axios:', {
    status: error.response.status,
    data: error.response.data,
  });
};
