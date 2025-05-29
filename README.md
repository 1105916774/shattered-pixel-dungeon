# shattered-pixel-dungeon-3.0.2 Quality Improvement

## 01 Smart Potion Recommendations - Healing Potion

When the HIT POINT is less than half of the total HP (<10), check whether there is a healing potion in the bag:

   - If there is a healing potion, the Log will prompt "Warning: Low health! Using healing potion."
     
   - If there is no healing potion, the Log will prompt "No healing potion."

Tip: Set the character born with a healing potion



https://github.com/user-attachments/assets/7bbc8e5b-ffec-46c0-829c-181513822434



### Design Pattern

Object-Oriented Programming (OOP): **Strategy + Factory Design Pattern**

**Strategy Design Pattern** - Encapsulate the checking logic of each potion independently (healing potion, strength potion, etc)

**Factory Design Pattern** - Maintain the instantiation logic of all potion strategies



![Strategy + Factory Design Pattern](https://github.com/user-attachments/assets/1082b448-3cf4-47b6-abaf-f86015f7d9a7)




------------------------------------

## 02 Implement a logging system that records key runtime events related to mob behaviour

The system must log the following event information with event timestamps and mob identifiers:

   - Mob spawn: Log when a mob is first added to the level.
     
   - State transitions: Log every time the mob’s state changes.
     
   - Alert status: Log when the mob becomes alerted.
     
   - Target assignment: Log when the mob sets or changes its target.
     

Logs must be clearly formatted, printed to the terminal, and persisted to a log file. Logging must not disrupt game performance.
