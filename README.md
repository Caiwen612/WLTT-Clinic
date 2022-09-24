
# WLTT Clinic
This is a simple clinic system for BACS2063 Data Structures and Algorithm.

#Developer
TAY CHAI BOON [@Caiwen612](https://github.com/Caiwen612) <br/>
LAN KE EN [@LanKeEn-TARC](https://github.com/LanKeEn-TARC) <br/>
LEE CHUN KAI [@BananaKing123](https://github.com/BananaKing123) <br/>
WILSON YAU KAI CHUN [@wilsonyau02](https://github.com/wilsonyau02) <br/> 
##System requirement
Minimum: Java SDK17 <br/>
IDE: IntelliJ IDEA 

##Package Description
[adt](./src/adt/) = Abstract data type specification and implementation <br/>
[client](./src/client/) = Client program that handle the module<br/>
[driver](./src/driver/) = Combine whole client program become a system<br/>
[entity](./src/entity/) = Entity of each object <br/>
[utility](./src/utility/) = validation feature and font color feature<br/>

##ADT that use in this program
Instructions: Read the java interface files for understand the method to manipulate each adt<br/>
[ArrayList](./src/adt/ArrayList.java) = [ListInterface](./src/adt/ListInterface.java) <br/> 
[ArrayQueue](./src/adt/ArrayQueue.java) = [QueueInterface.java](./src/adt/QueueInterface.java)<br/>
[ArrayStack](./src/adt/ArrayStack.java) = [StackInterface.java](./src/adt/StackInterface.java) <br/>
[SortedListDoublyLinkList](./src/adt/SortedDoublyLinkList.java) = [SortedListInterface.java](./src/adt/SortedListInterface.java) <br/>


##How to use our program
##[Main Menu](./src/driver/Driver.java) <br/>
![image](.\img\mainMenu.png) <br/>
Note: If the room is empty, you can go counter module to add patient <br/>
Press 1 for Counter Module <br/>
Press 2 for Doctor Module <br/>
Press 3 for Pharmacy Module <br/>
Press 4 for Payment Module <br/>
Press 5 for End program <br/>
##[Counter Module](./src/client/CounterManager.java)  <br/>
![image](.\img\counterMenu.png) <br/>
Press 1 for Search the previous patient and choose whether need to add into the room queue<br/>
Press 2 for Add new patient and choose whether need to add into the room queue<br/>
Press 3 for Edit the patient details by input the ic number<br/>
Press 4 for Delete the patient details by input the ic number and remove the patient from room queue <br/>
Press 5 for Display queue status of clinic <br/>
Press 6 for go back previous menu which is main menu <br/>
 
##[Doctor Module](./src/client/DoctorOperation.java)  <br/>
![image](.\img\doctorMenu.png) <br/>
Note: If the queue did not have patient, the current patient will be empty and only show certain menu <br/>
Press 1 for Check patient history<br/>
Press 2 for Add new Patient record and medical cart<br/>
Press 3 for Edit patient record<br/>
Press 4 for Add the appointment to this patient<br/>
Press 5 for Appointment Management <br/>
Press 6 for Release the patient from doctor room and the patient will automatically add into pharmacy queue and call next patient if exist<br/>
Press 7 for go back previous menu <br/>
##[Doctor Module(Appointment Management)](./src/client/DoctorOperation.java)  <br/>
![image](.\img\appointmentMenu.png) <br/>
Press 1 for Search the appointment at certain date<br/>
Press 2 for Display all the appointment<br/>
Press 3 for Update the appointment<br/>
Press 4 for Delete the appointment<br/>
Press 5 for go back previous menu <br/>


##[Pharmacy Module](./src/client/PharmacistOperation.java) <br/>
![image](.\img\pharmacyMenu.png) <br/>
Note: If the queue did not have patient, the current patient will be empty.<br/>
Press 1 for Allocate the medicine to patient and reduce the stock of pharmacy<br/>
Press 2 for Release the patient from pharmacy slide and the patient will automatically add into payment queue and handle next patient if exist<br/>
Press 3 for Medicine stock menu<br/>
Press 4 for Medicine menu<br/>
Press 5 for View Summary report of medicine <br/>
Press 6 for go back previous menu<br/>

##[Pharmacy Module(Medicine Stock Management)](./src/client/PharmacistOperation.java) <br/>
![image](img\medicineStockMenu.png) <br/>
Press 1 for Search the medicine<br/>
Press 2 for Sort the medicine based on dosage quantity <br/>
Press 3 for Add the stock for medicine<br/>
Press 4 for pharmacy menu<br/>


##[Pharmacy Module(Medicine Menu Management)](./src/client/PharmacistOperation.java) <br/>
![image](img\medicineMenu.png) <br/>
Press 1 for Add new medicine <br/>
Press 2 for Update the cost and price of medicine<br/>
Press 3 for Delete the medicine<br/>
Press 4 for pharmacy menu<br/>


##[Payment Module](./src/client/PaymentManager.java)  <br/>
![image](.\img\paymentMenu.png) <br/>
Note: If the queue did not have patient, the current patient will be empty <br/>
Press 1 for Print invoice for patient<br/>
Press 2 for Transaction menu<br/>
Press 3 for Record the payment from customer<br/>
Press 4 for Release the patient from payment queue and call next patient if exist<br/>
Press 0 for go back previous menu <br/>

##[Payment Module(Transaction Menu)](./src/client/PaymentManager.java)  <br/>
![image](.\img\transactionmenu.png) <br/>
Note: If the queue did not have patient, the current patient will be empty <br/>
Press 1 for Check the latest transaction<br/>
Press 2 for Search the transaction<br/>
Press 3 for Sort the transaction based on date<br/>
Press 4 for Check Summary report to see profit<br/>
Press 0 for go back previous menu <br/>






