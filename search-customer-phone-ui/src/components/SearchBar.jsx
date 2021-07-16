import React, { useEffect, useState } from "react";
import { Form } from "semantic-ui-react";
export const SearchBar = (props) => {
  const [filters, setFilters] = useState();
  const [countryOptions, setCountryOptions] = useState([]);
  const onInputChange = (currentFilters) => {
    props.onFilter(currentFilters);
  };
  const onCountryChanged = (event) => {
    let currentFilters = filters;
    if (event.target.value === "Select country") {
      delete filters["country_code"];
      currentFilters = filters;
    } else currentFilters = { ...filters, country_code: event.target.value };
    setFilters(currentFilters);
    onInputChange(currentFilters);
  };
  const onStateChanged = (event) => {
    let currentFilters = filters;
    if (event.target.value === "Select state") {
      delete filters["is_valid"];
      currentFilters = filters;
    } else currentFilters = { ...filters, is_valid: event.target.value };

    setFilters(currentFilters);
    onInputChange(currentFilters);
  };
  useEffect(() => {
    setCountryOptions(props.countries);
  }, [props]);

  return (
    <div className="search-bar ui segment">
      <Form>
        <h2> Phone number search</h2>
        <Form.Group widths="equal">
          <Form.Field
            label="Filter with country"
            control="select"
            onChange={onCountryChanged}
          >
            <option value={undefined}>Select country</option>
            {countryOptions?.map((country, i) => {
              return (
                <option value={country.code} key={i}>
                  {country.name}
                </option>
              );
            })}
          </Form.Field>
          <Form.Field
            label="Validate numbers"
            control="select"
            onChange={onStateChanged}
          >
            <option value={undefined} key={0}>
              Select state
            </option>
            <option value={"VALID"} key={2}>
              Valid
            </option>
            <option value={"NOT_VALID"} key={3}>
              In-Valid
            </option>
          </Form.Field>
        </Form.Group>
      </Form>
    </div>
  );
};
