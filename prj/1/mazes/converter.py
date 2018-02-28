# this will convert drawn out mazes into a valid input
# file for project 1

number_of_valids = 0
valid_locals = [" "]
start = ""
end = ""

row_num = 0
while(True):
  # will get all the input as one string
  row = input()
  if(row == "end"):
    break
  spot = 0
  for i in range(0, len(row), +2):
    # valid spot
    if(row[i] == 'X'):
      valid_locals.append(str(str(row_num) + " " + str(spot)))
      number_of_valids += 1
    # end
    elif(row[i] == 'E'):
      valid_locals.append(str(str(row_num) + " " + str(spot)))
      number_of_valids += 1
      end = str(str(row_num) + " " + str(spot))
    # start
    elif(row[i] == 'S'):
      valid_locals.append(str(str(row_num) + " " + str(spot)))
      number_of_valids += 1
      start = str(str(row_num) + " " + str(spot))
    spot += 1
  i = 0
  row_num += 1
  
# print everything in the correct format
print(number_of_valids)
for spot in valid_locals:
  print(spot)
print()
print(start)
print(end)