from locust import HttpUser, task, between,TaskSet
from locust.contrib.fasthttp import FastHttpUser
from locust.clients import HttpSession
import os,json

class WebsiteUser(HttpUser):
    wait_time = between(1, 2)
    ca_certs = os.path.join(os.path.dirname(os.path.abspath(__file__)), "nginx.crt")
    host="https://localhost:443"
    
    @task
    def get_vehicles(self):
        response = self.client.get("/get-vehicles", verify=True)  # Set verify to True
        print(response.text, flush=True)
    
    @task
    def list_financings(self):
        response = self.client.get("/financing-request?vehicleId=F123", verify=True)  # Set verify to True
        print(response.text, flush=True)

  


