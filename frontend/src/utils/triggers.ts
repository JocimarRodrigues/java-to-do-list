import { Notify } from 'quasar';

export const triggerSuccess = (msg: string) => {
  Notify.create({
    type: 'positive',
    message: msg,
  });
};

export const triggerNegative = (error: string) => {
  Notify.create({
    type: 'negative',
    message: error,
  });
};
