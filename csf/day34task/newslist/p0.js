p = new Promise(
    (resolve, reject) => {
        console.info('starting async task')

        // set to resolve after 3 seconds
        setTimeout(() => {
            resolve('HELLO WORLD')
            reject('Error! Error!')
        }, 2000)
    }
)

console.info('after promise')

p.then( value => {
        console.info('result from promise: ', value)
        return value
    }).then (value => {
        console.info('after then:', value)
        throw 5
    }).then (value => {
        console.info('>>> from p2', value)
        if (value > 5)
            return value
        throw "out of range"
}).catch(
    error => {
        console.info('error from promise:', error)
        throw "all good!"
    }).then (value => {
        console.info('after then:', value)
        throw 'error again!'
    }).then (value => {
        console.info('after catch', value)
}).catch(
    error => {
        console.info('second catch:', error)
})
// handles errors