import struct
import io


def main(d):
    dt = io.BytesIO(d)

    def A(data):
        data.read(3)
        ans = struct.unpack('>bIb', data.read(struct.calcsize('>bIb')))
        dict1 = {'A1': ans[0], 'A2': B(ans[1]), 'A3': ans[2]}

        print(dict1)
        return dict1



    def B(address):
        data = io.BytesIO(d)
        print(data.read(address))
        # C * 4 = 32
        res = struct.unpack('>q', data.read(struct.calcsize('>q')))
        dict1 = {'B1': res[0]}
        arr = []
        for i in range(4):
            arr.append(C(data))
        res = struct.unpack('>BI', data.read(struct.calcsize('>BI')))
        dict1['B2'] = arr
        dict1['B3'] = res[0]
        dict1['B4'] = res[1]
        print(dict1)
        return dict1

    def ARR(size, address):
        data = io.BytesIO(d)
        data.read(address)
        arr = []
        for i in range(size):
            arr.append((struct.unpack('>b', data.read(struct.calcsize('>b')))[0]))
        print(arr)
        return arr

    def C(data):
        res = struct.unpack('>bHHHb', data.read(struct.calcsize('>bHHHb')))
        dict1 = {'C1': res[0], 'C2': D(res[1]), 'C3': ARR(res[2], res[3]), 'C4': res[4]}
        print(res)
        return dict1

    def D(address):
        data = io.BytesIO(d)
        data.read(address)
        res = struct.unpack('>IIhhhhh', data.read(struct.calcsize('>IIhhhhh')))
        arr = [res[3], res[4], res[5], res[6]]
        dict2 = {'D1': ARRfloat(res[0], res[1]), 'D2': res[2], 'D3': arr}
        print(res)
        return dict2

    def ARRfloat(size, address):
        data = io.BytesIO(d)
        data.read(address)
        arr = []
        for i in range(size):
            arr.append((struct.unpack('>f', data.read(struct.calcsize('>f')))[0]))

        print(arr)
        return arr

    return A(dt)


data = (b'GWN\xe2\x00\x00\x00\x94Q?:\xff`\xbe\x8eb_\xbf\x1f\xa8"\x00\x00\x00'
 b'\x03\x00\x00\x00\tv\xcb\xc5\xce\xde\x05\x8e\xeb\xff\x8cJ\xfa<P\xb5'
 b'"\xbd\x81\x99\x0e>\x83\x13\xef\x00\x00\x00\x03\x00\x00\x00)\xfd\xb7\xca'
 b'\xf9\xce\x1bL\xa6\xaeq\xa5\xfe\xc2M?u\xe4\xc4>Zp\xf8\xbe\x99\xd3:?U#\xe1\x00'
 b'\x00\x00\x04\x00\x00\x00KP\x84\xf9\x87\xae\xbbL\xbf\r\xe3\xdd\xa9\xdb?M\xe63'
 b'?\x19p*\xbf\nK\xb6?\x10%\x1d\x00\x00\x00\x04\x00\x00\x00p\xa2\xe5\xa8e'
 b"E%\xb1\xc9\x9e\xebF\x1f,\xca\xce\xd4\x893]\xd8Z\x00\x15\x00\x02\x00'r"
 b'8\x005\x00\x04\x00Gv\xfb\x00[\x00\x03\x00m*\xfe\x00\x80\x00\x02\x00\x92\xdf'
 b'\xf8\xb0\x16FI')
main(data)
