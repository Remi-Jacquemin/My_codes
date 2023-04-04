@SETLOCAL ENABLEDELAYEDEXPANSION & python -x "%~f0" %* & EXIT /B !ERRORLEVEL!
import time
nb_trades = int(input("Combien y a-t-il de trades ? "))
liste_trades = []
for i in range(nb_trades):
    trade = float(input("Trade {} : ".format(i+1)))
    lot = float(input("Lot du trade {} : ".format(i+1)))
    liste_trades.append((trade, lot))

somme = 0
total_lots = 0
for x in liste_trades:
    somme += x[0]*x[1]
    total_lots += x[1]
result = round(somme/total_lots, 2)
print(result)
time.sleep(1000)