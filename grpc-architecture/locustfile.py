from locust import HttpUser, between, task

class MyUser(HttpUser):
    wait_time = between(1, 3)
    mail_otp = ""
    sms_otp = ""
    mail_verifying = ""
    phone_verifying = ""

    # Define the base host
    host = "https://localhost:443"

    @task
    def get_vehicles(self):
        self.client.get("/get-vehicles")

    @task
    def generate_otp(self):
        body = {   
            "name": "caludia",
            "surname": "cav",
            "phone": "324355253",
            "email": "clacav@gmail.com"
        }

        mail_verifying = body["email"]

        response = self.client.post("/generate-otp", json=body)
        self.otp = response.text
        print(response.text)
