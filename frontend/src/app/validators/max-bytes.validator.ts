import {ValidatorFn} from '@angular/forms';

export function maxBytes(max: number): ValidatorFn {
	return control => {
		const actual = new TextEncoder().encode(control.value || '').byteLength;
		if (actual > max) {
			return {
				maxBytes: {
					actual: actual,
					max: max
				}
			};
		}
		return null;
	};
}
