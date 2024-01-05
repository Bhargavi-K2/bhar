package practice;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



class Camera {
    String brand;
    String model;
    double rentalAmount;

    public Camera(String brand, String model, double rentalAmount) {
        this.brand = brand;
        this.model = model;
        this.rentalAmount = rentalAmount;
    }

}

class Wallet {
    double amount;
    ArrayList<Camera> rentedCamerasList = new ArrayList<>();

    public Wallet(double amount) {
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void addToRentedCameras(Camera camera) {
        rentedCamerasList.add(camera);
    }

    
}

public class Camerarentalapp {
    private ArrayList<Camera> cameraList = new ArrayList<>();
    private Wallet wallet;
    private Scanner scanner = new Scanner(System.in);

    public Camerarentalapp(double initialWalletAmount) {
        this.wallet = new Wallet(initialWalletAmount);
    }

    public void listCamera(Camera camera) {
        cameraList.add(camera);
    }

    public void viewMyCameras() {
        System.out.println("\nMy Cameras:");
        for (Camera camera : wallet.rentedCamerasList) {
            System.out.println(camera.brand + " " + camera.model);
        }
    }

    public void addCamera() {
        System.out.println("\nAdding a Camera:");
        System.out.print("Enter the camera brand: ");
        String brand = scanner.next();

        System.out.print("Enter the model: ");
        String model = scanner.next();

        System.out.print("Enter the per day price (INR): ");
        double price = scanner.nextDouble();

        Camera newCamera = new Camera(brand, model, price);
        listCamera(newCamera);
        System.out.println("Camera has been successfully added to the list.");
    }

    public void rentCamera() {
        System.out.println("\nAvailable Cameras:");
        
        if (cameraList.isEmpty()) {
            System.out.println("No cameras available for rent. Camera list is empty.");
            return;
        }

        for (int i = 0; i < cameraList.size(); i++) {
            Camera camera = cameraList.get(i);
            System.out.println((i + 1) + ". " + camera.brand + " " + camera.model +
                    " (Per Day: INR " + camera.rentalAmount + ")");
        }

        System.out.print("Enter the number of the camera to rent: ");
        int selectedCameraIndex = scanner.nextInt();

        if (selectedCameraIndex >= 1 && selectedCameraIndex <= cameraList.size()) {
            Camera selectedCamera = cameraList.get(selectedCameraIndex - 1);

            if (wallet.getAmount() >= selectedCamera.rentalAmount) {
                wallet.amount -= selectedCamera.rentalAmount;
                wallet.addToRentedCameras(selectedCamera);

                System.out.println("You have successfully rented " + selectedCamera.brand +
                        " " + selectedCamera.model + " for INR " + selectedCamera.rentalAmount + " per day.");
            } else {
                System.out.println("Insufficient funds in your wallet. Please add money.");
            }
        } else {
            System.out.println("Invalid camera selection. Please try again.");
        }
    }

    public void viewWalletAmount() {
        System.out.println("Wallet Amount: INR " + wallet.getAmount());
       
    }
    public void removeCamera() {
        System.out.println("\nRemoving a Camera:");

        if (wallet.rentedCamerasList.isEmpty()) {
            System.out.println("No cameras to remove. Your camera list is empty.");
            return;
        }
        System.out.println("Your Cameras:");
        for (int i = 0; i < wallet.rentedCamerasList.size(); i++) {
            Camera camera = wallet.rentedCamerasList.get(i);
            System.out.println((i + 1) + ". " + camera.brand + " " + camera.model);
        }

        System.out.print("Enter the number of the camera to remove: ");
        int selectedCameraIndex = scanner.nextInt();

        if (selectedCameraIndex >= 1 && selectedCameraIndex <= wallet.rentedCamerasList.size()) {
            Camera removedCamera = wallet.rentedCamerasList.remove(selectedCameraIndex - 1);
            System.out.println("Removed " + removedCamera.brand + " " + removedCamera.model + " from your cameras.");
        } else {
            System.out.println("Invalid camera selection. Please try again.");
        }
    }

        
    public void closeApplication() {
        System.out.println("Closing the Camera Rental Application. Goodbye!");
        System.exit(0);
    }

    public static void main(String[] args) {
        Camerarentalapp rentalApp = new Camerarentalapp(5000.0); 

        rentalApp.displayWelcomeScreen();

        while (true) {
            rentalApp.mainMenu();
        }
    }

   
    public void displayWelcomeScreen() {
    System.out.println("WELCOME TO CAMERA RENTAL APP |");

   
    System.out.print("USERNAME - ");
    String username = scanner.next();
    System.out.print("PASSWORD - ");
    String password = scanner.next();

    if (validateLogin(username, password)) {
        System.out.println("\nLogin successful. Welcome, " + username + "!\n");
        mainMenu();
    } else {
        System.out.println("\nInvalid credentials. Exiting application.");
        closeApplication();
    }
}

private boolean validateLogin(String username, String password) {
    return "admin".equals(username) && "admin123".equals(password);
}

public void mainMenu() {
    while (true) {
        System.out.println("MAIN MENU:");
        System.out.println("1. MY CAMERA");
        System.out.println("2. RENT A CAMERA");
        System.out.println("3. VIEW ALL CAMERAS");
        System.out.println("4. MY WALLET");
        System.out.println("5. EXIT");

        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                myCameraMenu();
                break;
            case 2:
                rentCamera();
                break;
            case 3:
                viewMyCameras();
                break;
            case 4:
                viewWalletAmount();
                break;
            case 5:
                closeApplication();
                break;
            default:
                System.out.println("Invalid choice. Please choose again.");
        }
    }
}

// ... (other methods)

public void myCameraMenu() {
    while (true) {
        System.out.println("\nMY CAMERA MENU:");
        System.out.println("1. ADD");
        System.out.println("2. REMOVE");
        System.out.println("3. VIEW MY CAMERAS");
        System.out.println("4. GO TO PREVIOUS MENU");

        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                addCamera();
                break;
            case 2:
                removeCamera();
                break;
            case 3:
                viewMyCameras();
                break;
            case 4:
                return;
            default:
                System.out.println("Invalid choice. Please choose again.");
        }
    }
}
}



