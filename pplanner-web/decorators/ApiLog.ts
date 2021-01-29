export function ApiLog (constructor: Function) {
	const instance = constructor.prototype
	for (let key of Object.getOwnPropertyNames(instance)) {
		if (key !== 'constructor') {
			let descriptor = Object.getOwnPropertyDescriptor(instance, key)
			if (descriptor) {
				let method = descriptor.value
				descriptor.value = function (...args: any[]) {
					console.warn(`${ instance.constructor.name }.${ key }()`)

					return method.apply(this, args)
				}
				Object.defineProperty(instance, key, descriptor)
			}
		}
	}
}
