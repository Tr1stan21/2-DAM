# 0 = free space
# 1 = obstacle
# 2 = dirt
# 3 = robot
room = [
    [3, 2, 0, 1, 0],
    [0, 1, 0, 2, 0],
    [2, 0, 0, 1, 0],
    [0, 2, 0, 0, 0],
    [1, 0, 0, 2, 0]
]
actual_position = [
    [0, 0]
]

def check_limits_x(x, y):
    if x < len(room[y]):
        return True
    return False

def check_limits_y(y):
    if y < len(room):
        return True
    return False

def move_robot(x, y):
    if room[y][x+1] == 2 and check_limits_x(x, y):
        room[y][x+1] == 0
        actual_position[0][0] = x+1
        actual_position[0][1] = y
    elif room[y+1][x] == 2 and check_limits_y(y):
        room[y+1][x] == 0
        actual_position[0][0] = x
        actual_position[0][1] = y+1
    elif room[y][x-1] == 2 and check_limits_x(x, y):
        room[y][x-1] == 0
        actual_position[0][0] = x-1
        actual_position[0][1] = y
    elif room[y-1][x] == 2 and check_limits_y(y):
        room[y-1][x] == 0
        actual_position[0][0] = x
        actual_position[0][1] = y-1

    elif room[y][x+1] == 0 and check_limits_x(x, y):
        room[y][x+1] == 0
        actual_position[0][0] = x+1
        actual_position[0][1] = y
    elif room[y+1][x] == 0 and check_limits_y(y):
        room[y+1][x] == 0
        actual_position[0][0] = x
        actual_position[0][1] = y+1
    elif room[y][x-1] == 0 and check_limits_x(x, y):
        room[y][x-1] == 0
        actual_position[0][0] = x-1
        actual_position[0][1] = y
    elif room[y-1][x] == 0 and check_limits_y(y):
        room[y-1][x] == 0
        actual_position[0][0] = x
        actual_position[0][1] = y-1

def print_room(room):    
    for i in room:
        print(i)

def check_room(room):
    for row in room:
        for column in row:
            if column == 2:
                return True
    return False
 
def main():
    print_room(room)
    while (check_room(room)):
            move_robot(actual_position[0][0], actual_position[0][1])

    print("Todo limpio")
    print_room(room)

main()