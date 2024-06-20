from locust import HttpUser, task, between
import os
import json

class WebsiteUser(HttpUser):
    wait_time = between(1, 2)
    ca_certs = os.path.join(os.path.dirname(os.path.abspath(__file__)), "nginx.crt")
    host = "https://localhost:443"

    @task
    def submit_form(self):
        # Fetching vehicle details
        get_vehicles = self.client.get("/get-vehicles", verify=False)

        # Fetching financing request details
        list_financings = self.client.get("/financing-request?vehicleId=F123", verify=False)

        # Creating a new practice
        customer_info = {
            "name": "test",
            "surname": "test",
            "email": "test@example.com",
            "phone": "33397432524"
        }
        headers = {'Content-Type': 'application/json'}
        practice_id_response = self.client.post("/create-practice?financingId=F001", json=customer_info, headers=headers, verify=False)
        practice_id = practice_id_response.text.strip()
        print(f"Practice ID: {practice_id}")

        # Adding additional information to the practice
        additional_info = {
            "job": "Software Developer",
            "gender": "male",
            "dateOfBirth": "1993-01-15",
            "province": "Taranto"
        }
        self.client.post(f"/additional-info?practiceId={practice_id}", json=additional_info, verify=False)

        # Posting user data
        user_data_path = os.path.join(os.path.dirname(os.path.abspath(__file__)), "payload.json")
        with open(user_data_path, 'r') as file:
            user_data = json.load(file)
        self.client.post(f"/user-data?practiceId={practice_id}", json=user_data, verify=False)

        # Evaluating the practice
        response = self.client.post(f"/evaluate-practice?practiceId={practice_id}", verify=False)

        self.client.get(f"/practice-overview?pracitceId={practice_id}",verify=False)


