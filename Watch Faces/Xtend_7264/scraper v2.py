import requests
import os
import time
from datetime import datetime

API_URL = "https://in-device.idoocloud.com/api/device/face/v4/get"
HEADERS = {
    "appkey": "548a50bc9f0a45d0bdfcdb5d194641d8",
    "X-HB-Client-Type": "ayb-android",
    "User-Agent": "okhttp/4.12.0"
}

TARGET_HW = "7264"
LOG_FILE = "scrape_log.txt"
DOWNLOAD_DIR = f"watchfaces_{TARGET_HW}"

BASE_PARAMS = {
    "language": "en",
    "appFaceVersion": "1",
    "supportFaceVersion": "3",
    "otaVersion": "15",
    "withoutVipFlag": "false",
    "os.type": "2",
    "os.version": "16",
    "d.name": TARGET_HW,
    "d.version": "15",
    "m.name": "M2101K7AI",
    "app.version": "3.2.1",
    "nid": "3128df22526d8185",
    "locale": "en_in",
    "country": "IN"
}

def write_to_log(message):
    timestamp = datetime.now().strftime("%Y-%m-%d %H:%M:%S")
    with open(LOG_FILE, "a", encoding="utf-8") as f:
        f.write(f"[{timestamp}] {message}\n")

def refined_scraper(start_id, end_id):
    if not os.path.exists(DOWNLOAD_DIR):
        os.makedirs(DOWNLOAD_DIR)

    # Initialize Log
    write_to_log(f"--- STARTING SCRAPE: IDs {start_id} to {end_id} (Target: {TARGET_HW}) ---")

    stats = {"total": 0, "matched": 0, "skipped": 0, "not_found": 0, "errors": 0}
    print(f"[*] Sweeping IDs {start_id} to {end_id} for Hardware: {TARGET_HW}...")

    for face_id in range(start_id, end_id + 1):
        params = BASE_PARAMS.copy()
        params["id"] = face_id

        try:
            response = requests.get(API_URL, headers=HEADERS, params=params, timeout=10)
            
            if response.status_code == 200:
                data = response.json()
                result = data.get("result")
                
                if not result or data.get("status") != 200:
                    msg = f"ID {face_id}: Doesn't exist"
                    print(f"[-] {msg}")
                    write_to_log(msg)
                    stats["not_found"] += 1
                else:
                    name = result.get("otaFaceName", "unknown")
                    
                    if TARGET_HW in name:
                        ota_version = result.get("otaFaceVersion")
                        if ota_version and ota_version.get("linkUrl"):
                            download_url = ota_version.get("linkUrl")
                            msg = f"ID {face_id}: MATCH FOUND -> {name}"
                            print(f"[+] {msg}")
                            write_to_log(msg + f" | URL: {download_url}")
                            download_file(download_url, name)
                            stats["matched"] += 1
                        else:
                            msg = f"ID {face_id}: Match found ({name}) but no download link!"
                            print(f"[!] {msg}")
                            write_to_log(msg)
                            stats["errors"] += 1
                    else:
                        msg = f"ID {face_id}: Skipped (Other HW: {name})"
                        print(f"[*] {msg}")
                        write_to_log(msg)
                        stats["skipped"] += 1
            else:
                msg = f"ID {face_id}: HTTP Error {response.status_code}"
                print(f"[!] {msg}")
                write_to_log(msg)
                stats["errors"] += 1
            
        except Exception as e:
            msg = f"ID {face_id}: Connection Error ({type(e).__name__})"
            print(f"[!] {msg}")
            write_to_log(msg)
            stats["errors"] += 1
            
        time.sleep(0.2) 

    summary = (
        f"\nFINAL REPORT FOR HARDWARE {TARGET_HW}\n"
        f"Matched: {stats['matched']}\n"
        f"Skipped: {stats['skipped']}\n"
        f"Not Found: {stats['not_found']}\n"
        f"Errors: {stats['errors']}"
    )
    print(summary)
    write_to_log(summary + "\n--- END OF SCRAPE ---\n")

def download_file(url, name):
    filename = os.path.join(DOWNLOAD_DIR, f"{name}.zip")
    if os.path.exists(filename):
        return
    
    try:
        r = requests.get(url, stream=True, timeout=15)
        with open(filename, 'wb') as f:
            for chunk in r.iter_content(chunk_size=8192):
                f.write(chunk)
    except:
        write_to_log(f"DOWNLOAD ERROR: Failed to save {name}")

if __name__ == "__main__":
    # Range (1, 5000) is usually enough to cover the entire IDO history
    refined_scraper(1, 5000)