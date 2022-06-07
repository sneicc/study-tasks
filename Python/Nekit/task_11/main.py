import struct
import io


def main(d):
    dt = io.BytesIO(d)

    def A(data):
        data.read(5)
        ans = struct.unpack('>QHIH', data.read(struct.calcsize('>QHIH')))
        dict1 = {'A1': ans[0], 'A2': ans[1], 'A3': ARR(ans[2], ans[3]), 'A4': (B(struct.calcsize('>QHIH')))}
        data.read(struct.calcsize('>dhHHHHHIHHbQ'))
        ans = struct.unpack('>bqH', data.read(struct.calcsize('>bqH')))
        dict1['A5'] = ans[0]
        dict1['A6'] = ans[1]
        dict1['A7'] = ans[2]
        print(dict1)
        return dict1

    def B(address):
        data = io.BytesIO(d)
        data.read(address + 5)

        res = struct.unpack('>dhHHHHHIHHbQ', data.read(struct.calcsize('>dhHHHHHIHHbQ')))
        dict1 = {'B1': res[0], 'B2': res[1]}
        arr = [C(res[2]), C(res[3]), C(res[4]), C(res[5]), C(res[6])]
        dict1['B3'] = arr
        dict1['B4'] = D(res[7])
        dict1['B5'] = ARRUINT8(res[8], res[9])
        dict1['B6'] = res[10]
        dict1['B7'] = res[11]

        return dict1

    def ARR(size, address):
        data = io.BytesIO(d)
        data.read(address)
        arr = []
        for i in range(size):
            arr.append((struct.unpack('>c', data.read(struct.calcsize('>c')))[0]))
        ans = ''
        for i in arr:
            ans += str(i, 'UTF-8')
        return ans

    def ARRUINT8(size, address):
        data = io.BytesIO(d)
        data.read(address)
        arr = []
        for i in range(size):
            arr.append((struct.unpack('>B', data.read(struct.calcsize('>B')))[0]))

        return arr


    def C(address):
        data = io.BytesIO(d)
        data.read(address)
        res = struct.unpack('>Bb', data.read(struct.calcsize('>Bb')))
        dict1 = {'C1': res[0], 'C2': res[1]}
        return dict1

    def D(address):
        data = io.BytesIO(d)
        data.read(address)
        res = struct.unpack('>bBHIQqIHfd', data.read(struct.calcsize('>bBHIQqIHfd')))
        dict2 = {'D1': res[0], 'D2': res[1], 'D3': ARRUINT8(res[2], res[3]), 'D4': res[4], 'D5': res[5], 'D6': ARRUINT32(res[6], res[7]), 'D7': res[8], 'D8': res[9]}
        return dict2

    def ARRUINT32(size, address):
        data = io.BytesIO(d)
        data.read(address)
        arr = []
        for i in range(size):
            arr.append((struct.unpack('>I', data.read(struct.calcsize('>I')))[0]))

        return arr

    return A(dt)


data = (b'\x0cKYBV\xb1\xf5\xb4\xa3\xfe#G\x89\x91\xe7\x00\x00\x00\x03\x00E\xbf\xdb\xdeX\xbcZ\xfe\x00j\xe0\x00H\x00J\x00L\x00N\x00P\x00\x00\x00^\x00\x02\x00\x88\xc9/2n(\xf8M\x82\x11\xa9\x83(\x93\x1b\xbb\xafT\xee\x8f\x8bbyr\x1d*\xbf2\xfcQ\x8d~\x9f\x04\x1b0\xca]\xdd\xe8\xeb\x19\x8bQ\x88 IW\x00\x04\x00\x00\x00RS\r\x1a\xb7\xdd7i\xcd\xfb\xa1Ge\xef7\x89\x99\x00\x00\x00\x02\x00V>KD\xfe?\xe2J3DybT\x8c\xdb')
main(data)
