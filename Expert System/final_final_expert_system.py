import pandas as pd

# Load datasets
flight_df = pd.read_csv("flight_dataset1.csv")
cargo_df = pd.read_csv("cargo_dataset.csv")

def find_available_flights(source, destination):
    return flight_df[(flight_df['source'] == source) & (flight_df['destination'] == destination)]

def find_cheapest_flight(source, destination):
    filtered_flights = find_available_flights(source, destination)
    return filtered_flights.loc[filtered_flights['ticket_price (USD)'].idxmin()] if not filtered_flights.empty else None

def calculate_total_revenue(source, destination):
    filtered_flights = find_available_flights(source, destination)
    return (filtered_flights['ticket_price (USD)'] * filtered_flights['passenger_capacity']).sum() if not filtered_flights.empty else None

def find_highest_revenue_cargo_flight():
    return cargo_df.loc[cargo_df['cargo_price (USD)'].idxmax()]

def calculate_total_cargo_weight_capacity(source, destination):
    filtered_cargo = cargo_df[(cargo_df['source'] == source) & (cargo_df['destination'] == destination)]
    return filtered_cargo['cargo_weight (kg)'].sum() if not filtered_cargo.empty else None

def book_flight(flight_id, num_passengers):
    flight_index = flight_df.index[flight_df['flight_id'] == flight_id].tolist()
    if not flight_index:
        print(f"No flight found with ID {flight_id}")
        return
    if flight_df.loc[flight_index[0], 'passenger_capacity'] >= num_passengers:
        flight_df.loc[flight_index[0], 'passenger_capacity'] -= num_passengers
        flight_df.to_csv("flight_dataset1.csv", index=False)
        print(f"\tFlight {flight_id} booked successfully for {num_passengers} passengers.")
    else:
        print(f"Sorry, the selected flight only has {flight_df.loc[flight_index[0], 'passenger_capacity']} seats available.")

def cancel_flight_booking(flight_id, num_passengers):
    flight_index = flight_df.index[flight_df['flight_id'] == flight_id].tolist()
    if not flight_index:
        print(f"No flight found with ID {flight_id}")
        return
    flight_df.loc[flight_index[0], 'passenger_capacity'] += num_passengers
    flight_df.to_csv("flight_dataset1.csv", index=False)
    print(f"\tBooking for {num_passengers} passengers on flight {flight_id} canceled successfully.")



def main_menu():
    while True:
        print("\n\n\n\t****************************************************************************************************")
        print("\t\t\t\t\t\t\tWELCOME")
        print("\t****************************************************************************************************")
        print("\t\t\t\t______________________________________________________")
        print("\n\t\t\t\t\t   Flight and Cargo Scheduling System")
        print("\t\t\t\t______________________________________________________\n\n")
        print("\t1. Book Flight.")
        print("\t2. Available Flights.")
        print("\t3. Cheapest Flight.")
        print("\t4. Total Revenue for Flights.")
        print("\t5. Highest Revenue Cargo Flight.")
        print("\t6. Total Cargo Weight Capacity.")
        print("\t7. Cancel Flight.")
        print("\t8. Exit.")

        choice = input("\tEnter your choice (1-7): ")

        if choice == "2":
            source = input("\n\tSource: ")
            destination = input("\tDestination: ")
            available_flights = find_available_flights(source, destination)
            print("\n\tAvailable Flights:\n")
            print(available_flights if not available_flights.empty else f"No flights found from {source} to {destination}")
            if continue_operation():
                continue

        elif choice == "3":
            source = input("\n\tSource: ")
            destination = input("\tDestination: ")
            cheapest_flight = find_cheapest_flight(source, destination)
            print("\n\tCheapest Flight:\n")
            print(cheapest_flight if cheapest_flight is not None else f"No flights found from {source} to {destination}")
            if continue_operation():
                continue

        elif choice == "4":
            source = input("\n\tSource: ")
            destination = input("\tDestination: ")
            total_revenue = calculate_total_revenue(source, destination)
            print(f"\n\tTotal revenue from {source} to {destination}: ${total_revenue}" if total_revenue is not None else f"No flights found from {source} to {destination}")
            if continue_operation():
                continue

        elif choice == "5":
            highest_revenue_cargo = find_highest_revenue_cargo_flight()
            print("\n\tHighest Revenue Cargo Flight:\n")
            print(highest_revenue_cargo)
            if continue_operation():
                continue

        elif choice == "6":
            source = input("\n\tSource: ")
            destination = input("\tDestination: ")
            total_cargo_weight = calculate_total_cargo_weight_capacity(source, destination)
            print(f"\n\tTotal cargo weight capacity from {source} to {destination}: {total_cargo_weight} kg" if total_cargo_weight is not None else f"No cargo flights found from {source} to {destination}")
            if continue_operation():
                continue

        elif choice == "1":
            flight_id = input("\n\tFlight ID: ")
            num_passengers = int(input("\tNumber of passengers: "))
            book_flight(flight_id, num_passengers)
            if continue_operation():
                continue

        elif choice == "8":
            print("\n\t........Thanks For Visiting........")
            break

        elif choice == "7":
            flight_id = input("\n\tFlight ID: ")
            num_passengers = int(input("\tNumber of passengers to cancel booking for: "))
            cancel_flight_booking(flight_id, num_passengers)
            if continue_operation():
                continue

        else:
            print("\tInvalid choice!!! Please try again.")

def continue_operation():
    while True:
        choice = input("\n\tDo you want to continue? (Y/N): ").strip().upper()
        if choice == 'Y':
            return True
        elif choice == 'N':
            return False
            
        else:
            print("\tInvalid choice! Please enter 'Y' or 'N'.")


if __name__ == "__main__":
    main_menu()
