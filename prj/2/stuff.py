# stuff.py

first_num = 0
second_num = 1
ans = 0
num = int(input("Input a number greater than 3: "))

for i in range(num - 2):
  ans = first_num + second_num
  first_num = second_num
  second_num = ans
print(ans)