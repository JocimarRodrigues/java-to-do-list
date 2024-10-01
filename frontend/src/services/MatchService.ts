import axios from 'axios';
import { environment } from 'src/environment/environment';
import { Match } from 'src/types/IMatch';

const instance = axios.create({
  baseURL: environment.baseUrl,
});

export function CreateMatch(team_a: string, type: number) {
  return instance.post('matches', {
    team_a,
    type,
  });
}

export function StartMatch(match_id: string) {
  return instance.post('matches/start', {
    match_id,
  });
}

export function PauseMatch(match_id: string, player_id: string) {
  return instance.post('matches/pause', {
    match_id,
    player_id,
  });
}

export function UnpauseMatch(match_id: string, player_id: string) {
  return instance.post('matches/unpause', {
    match_id,
    player_id,
  });
}

export function ConnectMatch(code: string, team: string) {
  return instance.post('matches/connect', {
    code,
    team,
  });


}

// let response = await axios.post('http://localhost:8080/matches', {
//   team_a: value.value,
//   type: 0
// })
