###
# Deployment of Trust Scheme/Trust-List
# DNS Record type URI

import requests
import os

tspa = 'https://tspa.pof-demo.example/tspa/api/v1'

scheme = 'federation.pof-demo.example'
listurl = 'https://pof-demo.example/trustlist.xml'

apiurl = '{}/{}/trust-list/'.format(tspa, scheme)

payload = encodePayload(listurl)

request('PUT', apiurl, data=payload)
