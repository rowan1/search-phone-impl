import axios from "axios";
export const BASE_URL = "http://127.0.0.1:8080";
const headers = new Headers();
headers.append("Content-Type", "application/json");
headers.append(
  "Access-Control-Allow-Methods",
  "GET,PUT,POST,DELETE,PATCH,OPTIONS"
);
headers.append("Referrer-Policy", "origin");

headers.append("Access-Control-Allow-Origin", "*");
headers.append("Access-Control-Allow-Credentials", "true");
export const fetchPhoneNumbers = (filters) => {
  let url = filters
    ? `${BASE_URL}/phone-numbers?${filters}`
    : `${BASE_URL}/phone-numbers`;
  return axios.get(url, headers);
};
export const fetchCountries = () => {
  return axios.get(`${BASE_URL}/countries`, headers);
};
