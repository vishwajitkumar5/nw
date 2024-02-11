import pandas as pd

# Read the text file
with open('C:/Users/vishwaku/indication/python_code_for_logs/10UE.txt', 'r') as file:
    lines = file.readlines()

# Initialize a list to store the timestamps
timestamps = []

# Extract timestamps for "Indication received" messages
for line in lines:
    if "Indication recieved" in line:
        timestamp = line.split(",")[1].split(": ")[1].strip()
        timestamps.append(timestamp)

# Convert timestamps to datetime objects
timestamps = [pd.to_datetime(timestamp, unit='ns') for timestamp in timestamps]

# Create a DataFrame
df = pd.DataFrame({'Indication Received Time': timestamps})

# Write DataFrame to Excel
df.to_excel('indication_received_times.xlsx', index=False)
