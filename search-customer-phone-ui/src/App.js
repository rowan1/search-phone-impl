import "semantic-ui-css/semantic.min.css";
import { SearchBar } from "./components/SearchBar";
import { PhoneNumberList } from "./components/PhoneNumberList";
import { useEffect, useState } from "react";
import { fetchPhoneNumbers, fetchCountries } from "./api/service";

function App() {
  const [numbers, setNumbers] = useState([]);
  const [countries, setCountries] = useState([]);
  const getAllCountries = async () => {
    let response = await fetchCountries();
    setCountries(response.data);
  };
  const getAllNumbers = async (filters) => {
    const response = await fetchPhoneNumbers(filters);
    let phoneNumbers = [];
    response.data.map((customer) => {
      customer.phone && phoneNumbers.push(customer.phone);
      return customer.phone;
    });
    setNumbers(phoneNumbers);
  };
  const onFilter = (filters) => {
    let params = "";
    for (let [key, value] of Object.entries(filters)) {
      params = `${params}${key}=${value}&`;
    }
    getAllNumbers(params);
  };
  useEffect(() => {
    getAllNumbers();
    getAllCountries();
  }, []);
  return (
    <div className="ui container">
      <SearchBar countries={countries} onFilter={onFilter} />
      <PhoneNumberList numbers={numbers} />
    </div>
  );
}

export default App;
