#-----------------------------------------------
# Project : Software synthesis using GA
# Hervo Pierre-Yves, automatic Script generated in java
#-----------------------------------------------
#-----------------------------------------------
Create Speaker... Robovox Female 2
Create Artword... phon 1.0
#-----------------------------------------------
# Supply lung energy
#-----------------------------------------------
Set target... 0.0    0.1 Lungs
Set target... 0.03     0.0 Lungs
Set target... 1.0     0.0 Lungs
#-----------------------------------------------
# Control glottis
#-----------------------------------------------
#Glottal closure
Set target... 0.0  0.5  Interarytenoid
Set target... 1.0  0.5 Interarytenoid
#
# Adduct vocal folds
Set target... 0.0   0.66 Cricothyroid
Set target... 1.0   0.91 Cricothyroid
# Close velopharyngeal port
#-----------------------------------------------
Set target... 0.0   0.29 LevatorPalatini
Set target... 1.0   0.48 LevatorPalatini
#-----------------------------------------------
#-----------------------------------------------
Set target... 0.0   0.45 Genioglossus
Set target... 1.0   0.11 Genioglossus
#
Set target... 0.0   0.16 Styloglossus
Set target... 1.0   0.76 Styloglossus
#
Set target... 0.0   0.59 Mylohyoid
Set target... 1.0   0.3 Mylohyoid
#
Set target... 0.0   0.25 OrbicularisOris
Set target... 1.0   0.96 OrbicularisOris
#-----------------------------------------------
# Shape mouth to open vowel
#-----------------------------------------------
# Lower the jaw
# -----------------------------------------
Set target... 0.0   0.24 Masseter
Set target... 1.0   -0.23 Masseter
# Pull tongue backwards
Set target... 0.0   0.92 Hyoglossus
Set target... 1.0   0.82 Hyoglossus
# Synthesise the sound
#-----------------------------------------------
select Artword phon
plus Speaker Robovox
To Sound... 22050 25   0 0 0    0 0 0    0 0 0
#-----------------------------------------------
#-----------------------------------------------
form Param
sentence value "default"
endform
Rename... 'value$'
#-----------------------------------------------
# Automatic data extraction part
To Formant (burg)... 0 5 5500 0.025 50
Speckle... 0 0 5500 30 yes
numberOfFormant = Get number of formants... 1
writeInfoLine("number of formants :", numberOfFormant)
for intervalNumber from 1 to 2 
temp1 = Get value at time... intervalNumber 0.1 Hertz Linear
temp1$ = string$(temp1)
appendInfoLine("Valeur de temp1 ",temp1$)
temp2 = Get value at time... intervalNumber 0.25 Hertz Linear
temp2$ = string$(temp2)
appendInfoLine("Valeur de temp2 ",temp2)
temp3 = Get value at time... intervalNumber 0.5 Hertz Linear
temp3$ = string$(temp3)
appendInfoLine("Valeur de temp3 ",temp3)
temp4 = Get value at time... intervalNumber 0.9 Hertz Linear
temp4$ = string$(temp4)
appendInfoLine("Valeur de temp4 ",temp4)
varMeans[intervalNumber] = Get mean... intervalNumber 0 0 Hertz
appendInfoLine("Valeur moyenne du formant ",intervalNumber,":",varMeans[intervalNumber])
