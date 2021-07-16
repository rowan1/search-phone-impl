import React from "react";
import { List } from "semantic-ui-react";

export const PhoneNumberList = (props) => {
  return (
    <List relaxed="very">
      {props.numbers?.map((number, i) => {
        return (
          <List.Item key={i}>
            <List.Content>
              <List.Header as="a">{number}</List.Header>
            </List.Content>
          </List.Item>
        );
      })}
    </List>
  );
};
