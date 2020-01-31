###
# Deployment of Trust Scheme Membership Claim
# DNS Record type PTR

import requests
import os

tspa = 'https://tspa.pof-demo.example/tspa/api/v1'

claimname = 'company-ca.pof-demo.example'
claimedscheme = 'federation.pof-demo.example'

url = '{}/{}/schemes'.format(tspa, claimname)

payload = encodePayload(claimedscheme)

request('PUT', url, data=payload)
