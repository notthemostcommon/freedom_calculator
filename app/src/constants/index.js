import Cookies from 'universal-cookie'; 
import { userStore } from '../globalStore/UserContext'; 
const cookies = new Cookies(); 
// export const API_BASE_URL = 'http://localhost:8080'; 
export const ACCESS_TOKEN = cookies.get("accessToken"); 
export const DEBT_LIST_URL = '/debts'; 
