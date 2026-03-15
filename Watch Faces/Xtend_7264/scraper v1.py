import requests
import os
import time

# The base API endpoint you discovered
API_URL = "https://in-device.idoocloud.com/api/device/face/v4/get"

# The authorization headers from your PCAP
HEADERS = {
    "appkey": "548a50bc9f0a45d0bdfcdb5d194641d8",
    "X-HB-Client-Type": "ayb-android",
    "User-Agent": "okhttp/4.12.0"
}

# The specific hardware parameters for your boAt Xtend (Device 7264)
BASE_PARAMS = {
    "language": "en",
    "appFaceVersion": "1",
    "supportFaceVersion": "3",
    "otaVersion": "15",
    "withoutVipFlag": "false",
    "os.type": "2",
    "os.version": "16",
    "d.name": "7264",
    "d.version": "15",
    "m.name": "M2101K7AI",
    "app.version": "3.2.1",
    "nid": "3128df22526d8185",
    "locale": "en_in",
    "country": "IN"
}

DOWNLOAD_DIR = "ido_watchfaces"

def download_watchfaces(start_id, end_id):
    if not os.path.exists(DOWNLOAD_DIR):
        os.makedirs(DOWNLOAD_DIR)

    print(f"[*] Starting scraper for IDs {start_id} to {end_id}...")

    for face_id in range(start_id, end_id + 1):
        params = BASE_PARAMS.copy()
        params["id"] = face_id

        try:
            # Hit the API
            response = requests.get(API_URL, headers=HEADERS, params=params, timeout=10)
            
            if response.status_code == 200:
                data = response.json()
                
                # Check if the server returned a valid watchface for this ID
                if data.get("status") == 200 and data.get("result"):
                    result = data["result"]
                    name = result.get("otaFaceName", f"unknown_face_{face_id}")
                    
                    # Dig into the JSON to find the AWS S3 download link
                    ota_version = result.get("otaFaceVersion", {})
                    download_url = ota_version.get("linkUrl")
                    
                    if download_url:
                        print(f"[+] Found ID {face_id}: {name}")
                        download_file(download_url, name)
                    else:
                        print(f"[-] ID {face_id} exists but has no download link.")
                else:
                    # ID doesn't exist or isn't compatible with device 7264
                    pass 
            else:
                print(f"[!] Server returned HTTP {response.status_code} for ID {face_id}")
            
        except Exception as e:
            print(f"[!] Error on ID {face_id}: {e}")
            
        # 0.5 second delay to avoid rate-limiting/IP bans from their firewall
        time.sleep(0.5)

def download_file(url, name):
    filename = os.path.join(DOWNLOAD_DIR, f"{name}.zip")
    
    # Skip if we already downloaded it
    if os.path.exists(filename):
        print(f"    -> Already downloaded: {name}.zip")
        return
        
    try:
        r = requests.get(url, stream=True, timeout=10)
        if r.status_code == 200:
            with open(filename, 'wb') as f:
                for chunk in r.iter_content(chunk_size=8192):
                    f.write(chunk)
            print(f"    -> Downloaded: {name}.zip")
        else:
            print(f"    -> Failed to download. HTTP {r.status_code}")
    except Exception as e:
        print(f"    -> Download error: {e}")

if __name__ == "__main__":
    # We know 1552 is valid. Let's sweep a 100-ID window around it to start.
    # You can change this range to 1 to 5000 to rip their entire database.
    download_watchfaces(1500, 1600)