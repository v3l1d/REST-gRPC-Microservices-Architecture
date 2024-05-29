from locust import HttpUser, task, between
import os
import json

class WebsiteUser(HttpUser):
    wait_time = between(1, 2)
    ca_certs = os.path.join(os.path.dirname(os.path.abspath(__file__)), "nginx.crt")
    host = "https://localhost:443"
    practiceId = "YD9217"
    practiceIdx = "OO1761"

    def get_vehicles(self):
        response = self.client.get("/get-vehicles", verify=self.ca_certs)  # Use self.ca_certs for SSL verification
        print(response.text, flush=True)


    def list_financings(self):
        response = self.client.get("/financing-request?vehicleId=F123", verify=self.ca_certs)  # Use self.ca_certs for SSL verification
        print(response.text, flush=True)



    def practice_overview(self):
        response = self.client.get(f"/practice-overview?practiceId={self.practiceId}", verify=False)  # Use self.ca_certs for SSL verification
        print(response.text, flush=True)
    @task
    def submit_form(self):
        # Load the JSON payload from a file
        payload_file_path = os.path.join(os.path.dirname(os.path.abspath(__file__)), "payload.json")
        with open(payload_file_path, 'r') as file:
            json_payload = json.load(file)

        # Modify the URL to include the practiceId parameter
        response = self.client.post(f"/evaluate-practice?practiceId={self.practiceId}", json=json_payload, verify=False)
        print(response.text, flush=True)

# Ensure you have the payload.json file in the same directory or adjust the path accordingly.
# Example payload.json content:
# {
#     "personalDetails": {
#         "surname": "Doe",
#         "firstName": "Jane",
#         "gender": "F",
#         ...
#     },
#     ...
# }
