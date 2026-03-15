import sys
from pathlib import Path
from datetime import datetime

def is_binary_file(file_path: Path) -> bool:
    """
    Heuristically detects if a file is binary or text.
    Reads the first 1024 bytes looking for a null byte (\x00).
    """
    try:
        with open(file_path, 'rb') as f:
            chunk = f.read(1024)
            # A null byte heavily indicates a compiled/binary or media file
            if b'\x00' in chunk:
                return True
    except Exception:
        # If we encounter an OS-level read error, default to treating it as binary to prevent crashes
        return True 
    return False

def main():
    # 1. Initialization & Variables
    root_dir = Path.cwd()
    script_name = Path(__file__).name
    output_index = "pathfiles.txt"
    output_code = "combined code.txt"
    
    # 2. Self-Exclusion Logic: Define files to ignore
    files_to_ignore = {script_name, output_index, output_code}
    
    print(f"Scanning directory: {root_dir}")
    print("Calculating total files for progress tracking...")
    
    # 3. Recursive Scanning & Progress Tracking: Count valid files first
    all_files = []
    for path in root_dir.rglob('*'):
        if path.is_file() and path.name not in files_to_ignore:
            all_files.append(path)
            
    total_files = len(all_files)
    if total_files == 0:
        print("No files found to process.")
        return

    # Sort files alphabetically for ordered indexing
    all_files.sort()
    
    print(f"Total valid files discovered: {total_files}\n")

    # 4. Open Output Files (Fixed line continuation here)
    with open(root_dir / output_index, 'w', encoding='utf-8') as f_index, \
         open(root_dir / output_code, 'w', encoding='utf-8') as f_code:
        
        # Output 2 Header: Root directory name/path and current Date & Time
        f_code.write(f"ROOT DIRECTORY: {root_dir}\n")
        f_code.write(f"TIMESTAMP: {datetime.now().strftime('%Y-%m-%d %H:%M:%S')}\n")
        f_code.write("=" * 80 + "\n\n")

        # 5. Iteration & File Processing
        completed_files = 0
        
        for file_path in all_files:
            # Output 1: Append to ordered index
            f_index.write(f"{file_path}\n")
            
            # Output 2: File Separation
            f_code.write("\n" + "-" * 80 + "\n")
            f_code.write(f"FILE NAME: {file_path.name}\n")
            f_code.write(f"FILE LOCATION: {file_path}\n")
            f_code.write("-" * 80 + "\n\n")
            
            # Content Logic (Code vs. Binary)
            if is_binary_file(file_path):
                f_code.write("<<non code containing file>>\n")
            else:
                try:
                    # Safely read and append text contents
                    with open(file_path, 'r', encoding='utf-8', errors='replace') as current_file:
                        f_code.write(current_file.read() + "\n")
                except Exception as e:
                    # Failsafe in case of severe locking or I/O issues
                    f_code.write(f"<<Error reading file: {e}>>\n")
            
            # Update Progress Tracking
            completed_files += 1
            percentage = (completed_files / total_files) * 100
            
            # Terminal feedback: carriage return (\r) overwrites the current line for real-time updates
            sys.stdout.write(f"\rProcessing [{completed_files}/{total_files} - {percentage:.2f}%]: {file_path.name[:30]:<30}")
            sys.stdout.flush()

    print("\n\nExecution complete. Output files generated successfully.")

if __name__ == "__main__":
    main()