from locust import HttpUser, task, between,TaskSet
from locust.contrib.fasthttp import FastHttpUser
from locust.clients import HttpSession
import os,json

class WebsiteUser(HttpUser):
    wait_time = between(1, 2)
    ca_certs = os.path.join(os.path.dirname(os.path.abspath(__file__)), "nginx.crt")
    host = "https://localhost:443"
    
    
    def get_vehicles(self):
        response = self.client.get("/get-vehicles", verify=False)  # Set verify to True
        print(response.text, flush=True)
    
    
    def list_financings(self):
        response = self.client.get("/financing-request?vehicleId=F123", verify=False)  # Set verify to True
        print(response.text, flush=True)

    @task
    def generate_otp(self):
        response = self.client.post("/generate-otp", json={"name":"john", "surname":"doe","email": "test@example.com", "phone": "1234567890"},verify=False)
       
        mail_otp = response.json()["MailOTP"]
        sms_otp = response.json()["SmsOtp"]
        print (response)
        verify_mail=self.client.post("/verify-otp?address=test@example.com", json={"mailOtp": mail_otp},verify=False)
        verify_sms=self.client.post("/verify-otp?address=test@example.com",json={"smsOtp": sms_otp},verify=False)
        print(verify_mail.text)
        print(verify_sms.text)


    
    @task
    def create_practice(self):
        response=self.client.post("/create-practice?financingId=F001", json={"name":"john","surname":"doe","email": "test@example.com", "phone": "1234567890"})
        print(response.text)
    
    @task
    def credit_card_paymanet(self):
        pass