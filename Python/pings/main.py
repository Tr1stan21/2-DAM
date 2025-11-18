import os

with open(os.path.join(os.path.dirname(os.path.abspath(__file__)),"ip_list.txt"), "r") as ipReader:
    
    for ip in ipReader:
        ip = ip.strip()
        response = os.popen(f"ping -n 2 {ip} ")
        print(response.read())

        with open(os.path.join(os.path.dirname(os.path.abspath(__file__)),"info_output.txt"), "a") as infoWriter:
                if("Tiempo de espera agotado para esta solicitud." or "inalcanzable") in response:
                    infoWriter.write(ip + " link is down\n")
                    print(response.read())

                else:
                    infoWriter.write(ip + " link is up\n")
                    print(response.read())

print("\ninfo_output content:")
with open(os.path.join(os.path.dirname(os.path.abspath(__file__)),"info_output.txt"), "r") as file:
    print(file.read())
with open(os.path.join(os.path.dirname(os.path.abspath(__file__)),"info_output.txt"), "w"):
    pass