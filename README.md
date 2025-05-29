# shattered-pixel-dungeon-3.0.2 Quality Improvement

## 01 Smart Potion Recommendations - Healing Potion

When the HIT POINT is less than half of the total HP (<10), check whether there is a healing potion in the bag:

   - If there is a healing potion, the Log will prompt "Warning: Low health! Using healing potion."
     
   - If there is no healing potion, the Log will prompt "No healing potion."

Tip: Set the character born with a healing potion

### Design Pattern

Object-Oriented Programming (OOP): **Strategy + Factory Design Pattern**

**Strategy Design Pattern** - Encapsulate the checking logic of each potion independently (healing potion, strength potion, etc)

**Factory Design Pattern** - Maintain the instantiation logic of all potion strategie

