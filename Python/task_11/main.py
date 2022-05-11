import struct
import io


def main(d):
    dt = io.BytesIO(d)

    def sign(data):
        if data.read(4) == b'CJR\xbd':
            return 0
        return -1

    def A(data):
        dict2 = {'A1': B(data)}
        data = io.BytesIO(d)
        data.read(62)
        dict2['A2'] = F(struct.unpack('<H', data.read(struct.calcsize('<H')))[0])
        print(dict2)
        return dict2

    def B(data):
        res = struct.unpack('<IdIIIIIIQdHI', data.read(58))
        arr = [D(res[2]), D(res[3]), D(res[4]), D(res[5])]
        dict2 = {'B1': C(res[0]), 'B2': res[1], 'B3': arr, 'B4': ARR(res[6], res[7]), 'B5': res[8], 'B6': res[9],
                 'B7': E(res[10]), 'B8': res[11]}
        print(dict2)
        return dict2

    def ARR(size, address):
        data = io.BytesIO(d)
        data.read(address)
        arr = []
        for i in range(size):
            arr.append((struct.unpack('<c', data.read(struct.calcsize('<c')))[0]))
        ans = ''
        for i in arr:
            ans += str(i, 'UTF-8')
        print(ans)
        return ans

    def C(address):
        data = io.BytesIO(d)
        data.read(address)
        res = struct.unpack('<bbh', data.read(struct.calcsize('<bbh')))
        dict2 = {'C1': res[0], 'C2': res[1], 'C3': res[2]}
        print(res)
        return dict2

    def D(address):
        data = io.BytesIO(d)
        data.read(address)
        res = struct.unpack('<Hd', data.read(struct.calcsize('<Hd')))
        dict2 = {'D1': res[0], 'D2': res[1]}
        print(res)
        return dict2

    def E(address):
        data = io.BytesIO(d)
        data.read(address)
        res = struct.unpack('<qqBBBBB', data.read(struct.calcsize('<qqBBBBB')))
        arr1 = [res[0], res[1]]
        arr2 = [res[3], res[4], res[5], res[6]]
        dict2 = {'E1': arr1, 'E2': res[2], 'E3': arr2}
        print(res)
        return dict2

    def F(address):
        data = io.BytesIO(d)
        data.read(address)
        res = struct.unpack('<fIHBd', data.read(struct.calcsize('<fIHBd')))
        dict2 = {'F1': res[0], 'F2': ARRfloat(res[1], res[2]), 'F3': res[3], 'F4': res[4]}
        print(res)
        return dict2

    def ARRfloat(size, address):
        data = io.BytesIO(d)
        data.read(address)
        arr = []
        for i in range(size):
            arr.append((struct.unpack('<f', data.read(struct.calcsize('<f')))[0]))

        print(arr)
        return arr

    sign(dt)
    return A(dt)


data = (b'CJR\xbd@\x00\x00\x00\xbc\xf4]\xee\xb8\xa7\xe4\xbfD\x00\x00\x00N\x00\x00\x00'
        b'X\x00\x00\x00b\x00\x00\x00\x06\x00\x00\x00l\x00\x00\x00\xd01V\x1f'
        b'0\x04\x17\x0b<\x82>\xd32\x0c\xd0?r\x00\xad9->\x93\x00\xa0\xd3\x96\xb5'
        b'J\xae\xb0\x917S\xf8^\xe7?\xb3g\xe0v\xe9\x1502\xab\xbf\xef\x8e\xe8\xc0'
        b'+\xb8\xe4\xe4\xe4?~:\xc00\xcbE[ \xc6\xbfofjimdg6\xfa\xd1\x82\x93<i!\x03'
        b'Qv\xd1\xd0\xbelg,\x99?\x08\xe7>\xbd>\xbb\xb5l\xbeV\xf7 \xbf\xfd\xccg\xbf\x03'
        b'\x00\x00\x00\x87\x00\xad\xc0\xea\x99\x1a\x82\xc9\xa7\xbf')
main(data)
