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
Set target... 0.0   0.52 Cricothyroid
Set target... 1.0   0.52 Cricothyroid
# Close velopharyngeal port
#-----------------------------------------------
Set target... 0.0   0.3 LevatorPalatini
Set target... 1.0   0.3 LevatorPalatini
#-----------------------------------------------
#-----------------------------------------------
Set target... 0.0   0.85 Genioglossus
Set target... 1.0   0.85 Genioglossus
#
Set target... 0.0   0.14 Styloglossus
Set target... 1.0   0.14 Styloglossus
#
Set target... 0.0   0.05 Mylohyoid
Set target... 1.0   0.05 Mylohyoid
#
Set target... 0.0   0.92 OrbicularisOris
Set target... 1.0   0.92 OrbicularisOris
#-----------------------------------------------
# Shape mouth to open vowel
#-----------------------------------------------
# Lower the jaw
# -----------------------------------------
Set target... 0.0   0.44 Masseter
Set target... 1.0   0.44 Masseter
# Pull tongue backwards
Set target... 0.0   0.81 Hyoglossus
Set target... 1.0   0.81 Hyoglossus
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
