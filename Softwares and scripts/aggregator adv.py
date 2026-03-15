import sys
import math
from pathlib import Path
from datetime import datetime

def is_binary_file(file_path: Path) -> bool:
    try:
        with open(file_path, 'rb') as f:
            chunk = f.read(1024)
            if b'\x00' in chunk:
                return True
    except Exception:
        return True 
    return False

def is_ignored(name: str, script_name: str) -> bool:
    # Ignores the script, the index file, and previously generated "code aggregator X.txt" files
    if name in {script_name, "pathfiles.txt"}: 
        return True
    if name.startswith("code aggregator ") and name.endswith(".txt"):
        # Extract the middle part to ensure it's a generated number
        middle = name[len("code aggregator "):-4]
        if middle.isdigit():
            return True
    return False

def main():
    # 1. Initialization
    root_dir = Path.cwd()
    script_name = Path(__file__).name
    output_index = "pathfiles.txt"
    
    print(f"Scanning directory: {root_dir}")
    print("Calculating total files for progress tracking...")
    
    # 2. Recursive Scanning
    all_files = []
    for path in root_dir.rglob('*'):
        if path.is_file() and not is_ignored(path.name, script_name):
            all_files.append(path)
            
    total_files = len(all_files)
    if total_files == 0:
        print("No files found to process.")
        return

    all_files.sort()
    print(f"Total valid files discovered: {total_files}\n")

    # 3. User Prompt & Math Logic
    try:
        num_splits = int(input(f"How many files should the code be split into? (1-{total_files}): "))
        num_splits = max(1, min(num_splits, total_files))
    except ValueError:
        print("Invalid input. Defaulting to 1.")
        num_splits = 1

    chunk_size = math.ceil(total_files / num_splits)

    # 4. Write Output 1 (Single Path Index)
    with open(root_dir / output_index, 'w', encoding='utf-8') as f_index:
        for file_path in all_files:
            f_index.write(f"{file_path}\n")

    # 5. Iteration & File Processing (Split Outputs)
    completed_files = 0
    
    for i in range(num_splits):
        start_idx = i * chunk_size
        end_idx = min(start_idx + chunk_size, total_files)
        chunk = all_files[start_idx:end_idx]
        
        if not chunk:
            break

        # Updated Naming Convention
        output_name = f"code aggregator {i + 1}.txt"
        
        with open(root_dir / output_name, 'w', encoding='utf-8') as f_code:
            # Output 2 Header
            f_code.write(f"PART: {i + 1}\n")
            f_code.write(f"ROOT DIRECTORY: {root_dir}\n")
            f_code.write(f"TIMESTAMP: {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}\n")
            f_code.write("=" * 80 + "\n\n")

            for file_path in chunk:
                # File Separation
                f_code.write("\n" + "-" * 80 + "\n")
                f_code.write(f"FILE NAME: {file_path.name}\n")
                f_code.write(f"FILE LOCATION: {file_path}\n")
                f_code.write("-" * 80 + "\n\n")
                
                # Content Logic
                if is_binary_file(file_path):
                    f_code.write("<<non code containing file>>\n")
                else:
                    try:
                        with open(file_path, 'r', encoding='utf-8', errors='replace') as current_file:
                            f_code.write(current_file.read() + "\n")
                    except Exception as e:
                        f_code.write(f"<<Error reading file: {e}>>\n")
                
                # Progress Tracking
                completed_files += 1
                percentage = (completed_files / total_files) * 100
                sys.stdout.write(f"\rProcessing [{completed_files}/{total_files} - {percentage:.2f}%]: {file_path.name[:30]:<30}")
                sys.stdout.flush()

    print("\n\nExecution complete. Split files generated successfully.")

if __name__ == "__main__":
    main()